import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ServiceService} from '../services/service.service';
import {ToastrService} from 'ngx-toastr';

@Component({
    selector: 'app-transfer-money',
    templateUrl: './transfer-money.component.html',
    styleUrls: ['./transfer-money.component.css']
})
export class TransferMoneyComponent implements OnInit {
    transferMoneyForm: FormGroup;

    constructor(
        private service: ServiceService,
        private toastr: ToastrService,
    ) {
    }

    ngOnInit(): void {
        this.transferMoneyForm = new FormGroup(
            {
                transferTo: new FormControl(null, Validators.required),
                amount: new FormControl(null, Validators.required)
            }
        );
    }

    transferMoneyFormSubmit() {
        const transferDetails = {
            transferTo: this.transferMoneyForm.value.transferTo,
            amount: this.transferMoneyForm.value.amount
        };
        this.service.transferMoney(transferDetails).subscribe(
            response => {
                this.toastr.success(response['message']);
            }
        )
    }
}
