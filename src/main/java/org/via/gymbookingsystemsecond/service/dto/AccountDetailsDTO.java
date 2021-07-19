package org.via.gymbookingsystemsecond.service.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Getter
public class AccountDetailsDTO implements UserDetails {

	@NonNull private AccountDTO account;
	@NonNull private String pass;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.<GrantedAuthority>emptyList();
	}

	@Override
	public String getPassword() {
		return pass;
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
