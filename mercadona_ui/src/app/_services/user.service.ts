import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  
  PATH_OF_API = 'http://localhost:9090';

  requestHeader = new HttpHeaders({ 'No-Auth': 'True',
                                    'Acces-Control-Allow-Origin': '*' });

  constructor(private httpclient: HttpClient) {}

  public login(logindata: any) {
    return this.httpclient.post(
      this.PATH_OF_API + '/api/auth/login',
      logindata,
      { headers: this.requestHeader }
    );
  }
}
