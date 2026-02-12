package com.example.TravelProject.auth;

import com.example.TravelProject.auth.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UsersService {

    @Autowired
	private UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // 유저 유무 확인 및 비밀번호 일치 확인
//    public UsersDto loginChk(String userId, String rawPassword) {
//        log.info("UsersService의 loginChk() 메소드 실행");
//        if (userId == null || rawPassword == null) return null;
//
//        Users user = usersRepository.findByUserId(userId)
//                .orElse(null);
//
//        if (user == null) {
//            return null;
//        }
//        if (!passwordEncoder.matches(rawPassword, user.getUserPassword())) {
//            return null;
//        }
//        return UsersDto.toDto(user);
//    }

    // 유저 고유 번호로 로그인한 유저 정보 가져오기
	public UsersDto selectIUser(Long userNum) {
		log.info("UsersService의 selectIUser() 메소드 실행");
		Users users = usersRepository.findById(userNum).orElse(null);
		// Entity 데이터를 Dto로 바꿔주기
		return users != null ? UsersDto.toDto(users) : null;
	}
	
	// 유저 아이디로 로그인한 유저 정보 가져오기
	public UsersDto findByUserId(String userId) {
		log.info("UsersService의 findByUserId() 메소드 실행");
        return usersRepository.findByUserId(userId)
                .map(UsersDto::toDto)
                .orElse(null);
	}

    @Transactional
	// 유저 정보 저장
	public void saveUser(UsersDto usersDto) {
		log.info("UsersService의 saveUser() 메소드 실행");
		// Dto를 Entity로 변환
		Users users = Users.toEntity(usersDto);
        users.setUserPassword(
                passwordEncoder.encode(usersDto.getUserPassword()) // 비밀번호 암호화
        );
		// 유저 정보 저장
		usersRepository.save(users);
	}
	
	// 아이디 중복 조회
	public String usersIdChk(String userId) {
		log.info("UsersService의 usersId() 메소드 실행");
		// 넘어온 아이디가 같은게 있는지 확인
		Users users = usersRepository.findByUserId(userId)
                .orElse(null);
        UsersDto usersDto = users != null ? UsersDto.toDto(users) : null;
		return usersDto != null ? usersDto.getUserId() : null;
	}

    // 이메일 중복 조회
    public String usersEmailChk(String userEmail) {
        log.info("UsersService의 usersEmail() 메소드 실행");
        // 넘어온 아이디가 같은게 있는지 확인
        Users users = usersRepository.findByUserEmail(userEmail)
                .orElse(null);
        UsersDto usersDto = users != null ? UsersDto.toDto(users) : null;
        return usersDto != null ? usersDto.getUserEmail() : null;
    }

    @Transactional
	// 유저 정보 수정
	public UsersDto updateInfo(UsersDto usersDto) {
		log.info("UsersService의 updateInfo() 메소드 실행");
//		log.info("usersDTO: {}", usersDTO);
		// 수정하려는 유저 정보가 있으면 얻어오고 없으면 예외를 발생시킨다.
		Users users = usersRepository.findById(usersDto.getUserNum())
				.orElseThrow(() -> new IllegalArgumentException("유저 정보 수정 실패! 대상 유저가 없습니다."));
		// 유저 정보 수정, 유저 정보 갱신
		users.update(usersDto);
		// 수정된 유저 정보로 다시 저장한다.
		Users updated = usersRepository.save(users);
		return UsersDto.toDto(updated);
	}

    @Transactional
	// 유저 정보 삭제
	public void deleteUsers(Long userNum) {
		log.info("UsersService의 deleteUsers() 메소드 실행");
		usersRepository.deleteById(userNum);
	}
	
}