package com.cloud.gemini.common;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * created by fufan on 2019-07-16 09:58
 **/
@Getter
public enum ResolverEnum {

    GUAVA("GUAVA"),
    REDIS("REDIS");

    String name;

    ResolverEnum(String name) {
        this.name = name;
    }

    public static ResolverEnum getResolverByName(String resolverName) {
        Optional<ResolverEnum> resolverEnum = Arrays.asList(ResolverEnum.values()).stream().filter((v) ->
            v.getName().equalsIgnoreCase(resolverName)
        ).findFirst();
        return resolverEnum.isPresent()?resolverEnum.get():null;
    }

    public static void main(String[] args) {
        System.out.println(getResolverByName("GUAVA"));
    }
}
