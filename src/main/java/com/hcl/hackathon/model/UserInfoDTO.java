package com.hcl.hackathon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;


/**
 * The persistent class for the user_info database table.
 * 
 */

@Getter
@Setter
public class UserInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer userId;


	private String userGroup;

	@Schema(description = "user Name",
			example = "user 1", required = false)
	@Size(max = 50)
	private String userName;

}