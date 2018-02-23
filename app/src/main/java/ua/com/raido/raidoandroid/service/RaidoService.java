package ua.com.raido.raidoandroid.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import ua.com.raido.raidoandroid.entity.HelloRasim;

public interface RaidoService {
    @GET("/")
    Observable<HelloRasim> getRasim();
}
