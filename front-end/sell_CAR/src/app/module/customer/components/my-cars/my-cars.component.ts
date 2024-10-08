import { Component } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-my-cars',
  templateUrl: './my-cars.component.html',
  styleUrls: ['./my-cars.component.scss']
})
export class MyCarsComponent {
  cars:any=[];
  constructor(private service:CustomerService,
    private message:NzMessageService
  ){}

  ngOnInit(){
    this.getCars();
  };
  getCars(){
    this.service.getMyCar().subscribe((res)=>{
      console.log(res);
      this.cars=res;
    })
  }
  deleteCar(id:number){
    this.service.deleteCar(id).subscribe((res)=>{
      this.message.success("Car deleted succesfully",{nzDuration:5000});
      this.getCars(); 
    })

  }


}
