package org.via.gymbookingsystemsecond.persistence.hateoas;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.via.gymbookingsystemsecond.persistence.Repository;

@Getter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
public abstract class HateoasRepository<T> implements Repository<T> {

	@Autowired protected RestTemplate rest;

	@Value("${org.via.gymbookingsystemsecond.dataUrl}")
	private String url;

	@NonNull private String slug;

	@NonNull private ParameterizedTypeReference<EntityModel<T>> typeToken;
	@NonNull private ParameterizedTypeReference<CollectionModel<EntityModel<T>>> collectionTypeToken;

	@Override
	public List<T> findAll() {
		return getCollection(url());
	}

	@Override
	public T add(T entity) {
		return rest.exchange(url(),
				HttpMethod.POST,
				new HttpEntity<>(entity),
				typeToken)
			.getBody()
			.getContent();
	}

	@Override
	public T update(T entity) {
		return rest.exchange(
				url(entity),
				HttpMethod.PATCH,
				new HttpEntity<>(entity),
				typeToken)
			.getBody()
			.getContent();
	}

	@Override
	public void remove(T entity) {
		rest.delete(url(entity));
	}


	protected Optional<T> getItem(String url) {
		return Optional.ofNullable(rest.exchange(
					url,
					HttpMethod.GET,
					null,
					typeToken)
				.getBody()
				.getContent());
	}

	protected List<T> getCollection(String url) {
		return rest.exchange(url,
				HttpMethod.GET,
				null,
				collectionTypeToken)
			.getBody()
			.getContent()
			.stream()
			.map(EntityModel::getContent)
			.collect(Collectors.toList());
	}

	protected String url(T entity) {
		return String.format("%s%s", url(), getId(entity));
	}

	protected String url(String slug) {
		return String.format("%s%s", url(), slug);
	}

	protected String url() {
		return String.format("%s%s/", url, slug);
	}

	protected abstract String getId(T entity);

}
