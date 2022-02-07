import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { SNACKBAR_CLOSE, SNACKBAR_ERROR, SNACKBAR_ERROR_OPTIONS, SNACKBAR_SUCCESS_OPTIONS } from '../constants/snackbar';
import { PayService } from '../pay.service';

@Component({
  selector: 'app-bank',
  templateUrl: './bank.component.html',
  styleUrls: ['./bank.component.css']
})
export class BankComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    public payService: PayService,
    public snackBar: MatSnackBar
  ) { }

  bankPending = false;
  paymentId = '';
  bankForm: FormGroup = new FormGroup({
    panNumber: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\d{16}'))]),
    cardHolderName: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\S'))]),
    expiratoryDate: new FormControl('', [Validators.required,
    Validators.pattern(new RegExp('\\d{2}/\\d{2}'))]),
    securityCode: new FormControl('', [Validators.required,
      Validators.pattern(new RegExp('\\d{3}'))]),
  });

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.paymentId = params.get('id');
      console.log(this.paymentId);
    })
  }

  pay(){
    if (this.bankForm.invalid){
      return;
    }
    this.bankPending = true;
    this.payService.pay(this.bankForm.value, this.paymentId).subscribe(
      (res) => {
        if (res.status == 200) {
          console.log(res);
          this.bankPending = false;
          this.snackBar.open("Transaction successful!", SNACKBAR_CLOSE, SNACKBAR_SUCCESS_OPTIONS);
        }
      }
    ,(err) => {
      this.bankPending = false;
      this.snackBar.open(SNACKBAR_ERROR, SNACKBAR_CLOSE, SNACKBAR_ERROR_OPTIONS);
    });
      
  }

}
