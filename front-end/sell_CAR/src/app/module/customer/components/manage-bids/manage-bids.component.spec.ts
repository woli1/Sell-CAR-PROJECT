import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageBidsComponent } from './manage-bids.component';

describe('ManageBidsComponent', () => {
  let component: ManageBidsComponent;
  let fixture: ComponentFixture<ManageBidsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManageBidsComponent]
    });
    fixture = TestBed.createComponent(ManageBidsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
