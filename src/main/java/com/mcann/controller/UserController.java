package com.mcann.controller;

import static com.mcann.constant.RestApis.*;

import com.mcann.dto.request.RegisterRequestDto;
import com.mcann.dto.request.UpdateUserProfileRequestDto;
import com.mcann.dto.response.BaseResponse;
import com.mcann.entity.User;
import com.mcann.exception.ErrorType;
import com.mcann.exception.LondonCityCardException;
import com.mcann.service.UserService;
import com.mcann.views.VwUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@PostMapping(REGISTER)
	public ResponseEntity<BaseResponse<Boolean>> register(@RequestBody RegisterRequestDto dto){
		if (!dto.password().equals(dto.rePassword())){
			throw new LondonCityCardException(ErrorType.INVALID_PASSWORD);
		}
		userService.register(dto);
		return ResponseEntity.ok(
				BaseResponse.<Boolean>builder()
				            .code(200)
				            .success(true)
				            .message("Kayıt başarıyla gerçekleştirildi.")
				            .data(true)
				            .build()
		);
	}
	
	@GetMapping(FINDALL)
	public ResponseEntity<BaseResponse<List<User>>> allUser() {
		return ResponseEntity.ok(BaseResponse.<List<User>>builder()
		                                     .success(true)
		                                     .data(userService.getAllUsers())
		                                     .code(200)
		                                     .message("Kullanıcılar başarıyla listelendi")
		                                     .build()
		);
	}
	
	@GetMapping(FINDALLRESPONSE)
	public ResponseEntity<BaseResponse<List<VwUser>>> getAllUserVwUsers() {
		return ResponseEntity.ok(
				BaseResponse.<List<VwUser>>builder()
				            .success(true)
				            .data(userService.getAllVwUser())
				            .code(200)
				            .message("Kullanıcılar başarıyla listelendi")
				            .build()
		);
	}
	
	@PutMapping(UPDATE)
	public ResponseEntity<BaseResponse<Boolean>> updateUserProfile(@RequestBody UpdateUserProfileRequestDto dto){
		userService.update(dto);
		return ResponseEntity.ok(
				BaseResponse.<Boolean>builder()
				            .message("Güncelleme işlemi başarıyla kaydedildi")
				            .code(200)
				            .success(true)
				            .data(true)
				            .build()
		);
	}
	
}