import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { Card } from './model/card';

@Injectable({
  providedIn: 'root'
})
export class PayService {

  constructor(
    private http: HttpClient
    ) { }

  readonly API_PAY: string = 'http://localhost:8001/api/payment/';

  pay(card: Card, id: string): Observable<any>{
    return this.http.post<Card>(this.API_PAY + id, card,{ observe: 'response' }).pipe(
      catchError(() => of(null))
    );
  }
}
