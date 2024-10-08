import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth/services/storage/storage.service';


const BASE_URL="http://localhost:8081/";
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http:HttpClient) { }

  getAllCars():Observable<any>{
    return this.http.get(BASE_URL+'api/admin/cars',{
      headers:this.createAuthorizationHeader()
    })

  }
  //http://localhost:8081/api/admin/cars

  getBids():Observable<any>{
    return this.http.get(BASE_URL+"api/admin/car/bids",{
      headers:this.createAuthorizationHeader()
    })
  }

  
  createAuthorizationHeader():HttpHeaders{
    let authHeaders:HttpHeaders=new HttpHeaders();
    return authHeaders.set(
      "Authorization",
      "Bearer "+StorageService.getToken()
    );
  }
  searchCar(searchDto:any):Observable<any>{
    return this.http.post(BASE_URL+"api/admin/car/search",searchDto,{
      headers:this.createAuthorizationHeader()
    })    
  }


}
