import {Component, OnInit} from '@angular/core';
import {ServiceService} from '../services/service.service';
import {formatDate} from '@angular/common';

@Component({
    selector: 'app-transaction-history',
    templateUrl: './transaction-history.component.html',
    styleUrls: ['./transaction-history.component.css']
})
export class TransactionHistoryComponent implements OnInit {
    transactions: any;
    constructor(private service: ServiceService) {
    }

    ngOnInit(): void {
      this.getTransactions();
    }

    getTransactions(){
      this.service.getTransactions().subscribe(
          response => {

            this.transactions = response;
            console.log(this.transactions);
          }
      );

    }

  formatDate(date) {
    return formatDate(date, 'mediumDate', 'en-us', '+530');
  }
}
