package org.via.gymbookingsystemsecond.hateoas;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
public abstract class HateoasRepository<T, F> {

	@Autowired
	@Getter(AccessLevel.PROTECTED)
	private HateoasDataClient client;

	@Value("${org.via.gymbookingsystemsecond.dataUrl}")
	private String dataUrl;

	@NonNull private String prefix;

	@NonNull private ParameterizedTypeReference<EntityModel<T>> ptr;
	@NonNull private ParameterizedTypeReference<CollectionModel<EntityModel<T>>> collectionPtr;

	public Optional<T> add(F obj) {
		return client.post(url(), obj, ptr)
			.map(this::map)
			.map(EntityModel::getContent);
	}

	public Optional<T> get(String path) {
		return client.get(url(path), ptr)
			.map(this::map)
			.map(EntityModel::getContent);
	}


	protected String url(String slug) {
		return String.format("%s%s%s", dataUrl, prefix, slug);
	}

	protected String url() {
		return String.format("%s%s", dataUrl, prefix);
	}

	protected String id(String path) {
		return path.substring(url().length());
	}

	protected EntityModel<T> map(EntityModel<T> em) {
		return em;
	}
}
