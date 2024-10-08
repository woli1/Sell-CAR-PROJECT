import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-get-bookings',
  templateUrl: './get-bookings.component.html',
  styleUrls: ['./get-bookings.component.scss']
})
export class GetBookingsComponent {
  bids:any;
  isSpinning:boolean=false;

  constructor(private service:AdminService){}
  ngOnInit(){
    this.getMyBids();
  };
  getMyBids(){
    this.isSpinning=true;
    this.service.getBids().subscribe((res)=>{
      this.isSpinning=false;
      console.log(res);
      this.bids=res;
    })
  }
}
