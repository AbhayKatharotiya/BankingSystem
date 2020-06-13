import {Component, OnInit} from '@angular/core';
import {ServiceService} from '../services/service.service';
import {NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
import {ToastrService} from 'ngx-toastr';

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

    public email: string;
    public name: string;
    public contactNo: string;
    public dob: Date;
    public ngbDate: NgbDateStruct;
    public age: number;
    public initialized: boolean;

    constructor(
        private service: ServiceService,
        private toastr: ToastrService,
    ) {
        this.initialized = false;
    }

    ngOnInit(): void {
        this.service.refreshNeeded$.subscribe(() => {
            this.fetchProfile();
        });
        if (!this.initialized) {
            this.fetchProfile();
            this.initialized = true;
        }
    }

    fetchProfile() {
        this.service.onFetchProfile().subscribe(
            response => {
                this.email = response['email'];
                this.name = response['name'];
                this.dob = new Date(response['dob']);
                this.contactNo = response['contactNo'];
                this.age = response['age'];
                this.ngbDate = {year: this.dob.getFullYear(), month: this.dob.getMonth() + 1, day: this.dob.getDate()};
            }
        );
    }

    updateProfile() {
        const userDetails = {
            name: this.name,
            email: this.email,
            dob: this.ngbDate.year + '-'
                + (this.ngbDate.month < 10 ? '0' + this.ngbDate.month : this.ngbDate.month) + '-'
                + (this.ngbDate.day < 10 ? '0' + this.ngbDate.day : this.ngbDate.day),
            contactNo: this.contactNo
        };
        console.log(userDetails);
        this.service.onUpdateProfile(userDetails).subscribe(
            response => {
                this.toastr.success(response['message']);
            }
        );
    }
}
