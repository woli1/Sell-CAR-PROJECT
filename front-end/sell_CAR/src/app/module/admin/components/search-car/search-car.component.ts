import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CustomerService } from 'src/app/module/customer/services/customer.service';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-search-car',
  templateUrl: './search-car.component.html',
  styleUrls: ['./search-car.component.scss']
})
export class SearchCarComponent {


  listOfBrands=["BMW","AUDI","FERRARI","TESLA","VOLVO","TOYOTA","HONDA","FORD","NISSAN","HYUNDAI","LEXUS","HAVAL"];
  listOfType=["Petrol","Hybrid","Diesel","Electric","CNG"];
  listOfColor=["Red","White","Blue","Black","Orange","Grey","Silver"];
  listOfTransmission=["Manual","Automatic"];

  searchCarForm:FormGroup;
  isSpinning:boolean=false;
  cars:any[]=[];


  constructor(private service:AdminService,
    private fb:FormBuilder,
    private router:Router,
    private message:NzMessageService

  ){}


  ngOnInit(){
    this.searchCarForm=this.fb.group({
      brand:[null],
      type:[null],
      transmission:[null],
      color:[null],
      
      
    })

  }

  
  searchCar(){
    this.isSpinning=true;
    this.cars=[];
    this.service.searchCar(this.searchCarForm.value).subscribe((res)=>{
      this.isSpinning=false;
      this.cars=res;
       
    })
  }


}
