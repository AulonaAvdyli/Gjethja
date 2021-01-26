package com.katrasolutions.gjethja.repository;

import com.katrasolutions.gjethja.entity.Provider;

import java.util.List;

public interface ProviderRepository extends UserRepository<Provider> {

    List<Provider> findAllByCity(String city);
    List<Provider> findAllByEducation(String education);
    List<Provider> findAllByCityAndEducation(String city, String education);
}
