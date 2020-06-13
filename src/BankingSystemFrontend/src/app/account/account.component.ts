import {Component, OnInit} from '@angular/core';
import {ServiceService} from '../services/service.service';

@Component({
    selector: 'app-account',
    templateUrl: './account.component.html',
    styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {
    accountNo: string;
    balance: number;

    constructor(
        private service: ServiceService,
    ) {
    }

    ngOnInit(): void {
        this.fetchAccountDetails();
    }

  private fetchAccountDetails() {
      this.service.onGetAccount().subscribe(
          response => {
              this.accountNo = response['accountNo'];
              this.balance = response['balance'];
          }
      );
  }
}
