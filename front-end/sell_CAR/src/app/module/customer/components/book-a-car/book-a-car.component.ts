import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CustomerService } from '../../services/customer.service';
import { StorageService } from 'src/app/auth/services/storage/storage.service';

@Component({
  selector: 'app-book-a-car',
  templateUrl: './book-a-car.component.html',
  styleUrls: ['./book-a-car.component.scss']
})
export class BookACarComponent {
  id:number=this.activateRoute.snapshot.params["id"];
  car:any;
  bidForm:FormGroup;
  isSpinning:boolean=false;
  constructor(private service:CustomerService,
    private activateRoute:ActivatedRoute,
    private fb:FormBuilder,
    private router:Router,
    private message:NzMessageService
  ){  }
  ngOnInit(){
    this.getCar();
    this.bidForm=this.fb.group({
      price:[null,[Validators.required]]
    })

  };
  getCar(){
    this.service.getCarById(this.id).subscribe((res)=>{
      console.log(res);
      this.car=res;
    })
  }

  bidACar(FormData:any){
    this.isSpinning=true;
    const obj={
      price:FormData.price,
      userId:StorageService.getUserId(),
      carId:this.id
    };
    this.service.bidACar(obj).subscribe((res)=>{
      this.isSpinning=false;
      this.message.success("Car posted successfully",{nzDuration:5000});
      this.router.navigateByUrl("/customer/dashboard");
      
    },error=>{
      this.message.error("Something went wrong",{nzDuration:5000});
    })
  }
}
