import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { StorageService } from 'src/app/auth/services/storage/storage.service';

@Component({
  selector: 'app-update-car',
  templateUrl: './update-car.component.html',
  styleUrls: ['./update-car.component.scss']
})
export class UpdateCarComponent {

  listOfBrands=["BMW","AUDI","FERRARI","TESLA","VOLVO","TOYOTA","HONDA","FORD","NISSAN","HYUNDAI","LEXUS","HAVAL"];
  listOfType=["Petrol","Hybrid","Diesel","Electric","CNG"];
  listOfColor=["Red","White","Blue","Black","Orange","Grey","Silver"];
  listOfTransmission=["Manual","Automatic"];

  updateCarForm:FormGroup;
  isSpinning:boolean=false;
  selectedFile:File|null;
  imagePreview:string|ArrayBuffer|null;
  imageChanged:boolean=false;


  id:number=this.activatedRoute.snapshot.params["id"];
  
  existingImage:string|null=null;
  constructor(private service:CustomerService,
    private activatedRoute:ActivatedRoute,
    private fb:FormBuilder,
    private  router:Router,
    private message:NzMessageService
  ){}
  ngOnInit(){
    this.updateCarForm=this.fb.group({
      brand:[null,[Validators.required]],
      name:[null,[Validators.required]],
      type:[null,[Validators.required]],
      transmission:[null,[Validators.required]],
      color:[null,[Validators.required]],
      year:[null,[Validators.required]],
      description:[null,[Validators.required]],
      price:[null,[Validators.required]],
    })
    this.getCars();
  }
  getCars(){
    this.service.getCarById(this.id).subscribe((res)=>{
      console.log("hhh");
      console.log(res);
      
      this.existingImage='data:image/jpeg;base64,'+res.returnedImg;
      this.updateCarForm.patchValue(res);
    })
  }
  updateCar(){
    this.isSpinning=true;
    const formData:FormData=new FormData();

    formData.append("img",this.selectedFile);
    formData.append("name",this.updateCarForm.get('name').value);
    formData.append("brand",this.updateCarForm.get('brand').value);
    formData.append("type",this.updateCarForm.get('type').value);
    formData.append("color",this.updateCarForm.get("color").value);
    formData.append("model",this.updateCarForm.get('year').value);
    formData.append("transmission",this.updateCarForm.get('transmission').value);
    formData.append("description",this.updateCarForm.get("description").value);
    formData.append('price',this.updateCarForm.get("price").value);
    formData.append("userId",StorageService.getUserId());
    this.service.updateCar(this.id,formData).subscribe((res)=>{
      this.isSpinning=false;
      this.message.success("Car updated succesfully",{nzDuration:5000});
      this.router.navigateByUrl("/customer/dashboard");

    },error=>{
      this.message.error("Something went wrong",{nzDuration:5000})
    })

  }
  onFileSelected(event:any){
    this.selectedFile=event.target.files[0];
    this.previewImage();
    this.imageChanged=true;
    this.existingImage=null;

  }
  previewImage(){
    const reader=new FileReader();
    reader.onload=()=>{
      this.imagePreview=reader.result;
    }
    reader.readAsDataURL(this.selectedFile);
  }

}
