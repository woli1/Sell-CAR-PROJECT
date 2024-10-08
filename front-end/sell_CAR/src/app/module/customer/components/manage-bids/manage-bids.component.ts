import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-manage-bids',
  templateUrl: './manage-bids.component.html',
  styleUrls: ['./manage-bids.component.scss']
})
export class ManageBidsComponent {
  bids:any;
  isSpinning:boolean=false;
  id:number=this.activatedRoute.snapshot.params["id"];
  constructor(private service:CustomerService
    ,private router:Router,
    private message:NzMessageService,
    private activatedRoute:ActivatedRoute
  ){}
  getBidsByCarId(){
    this.isSpinning=true;
    this.service.getBidsByCarId(this.id).subscribe((res)=>{
      this.isSpinning=false;
      console.log(res);
      this.bids=res;
      
    })
    

  }
  ngOnInit(){
    this.getBidsByCarId();
  }
  
  
  changeBookingStatus(id:number,status:string){
    this.isSpinning=true;
    this.service.updateBidsStatus(id,status).subscribe((res)=>{
      this.isSpinning=false;
      this.message.success("Bid status changed  successfully",{nzDuration:5000});
      this.router.navigateByUrl("/customer/dashboard");
      this.getBidsByCarId();

    },error=>{
      this.message.error("Something went wrong",{nzDuration:5000})
    })
  }
}
