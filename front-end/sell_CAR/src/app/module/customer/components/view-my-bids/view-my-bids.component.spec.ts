import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMyBidsComponent } from './view-my-bids.component';

describe('ViewMyBidsComponent', () => {
  let component: ViewMyBidsComponent;
  let fixture: ComponentFixture<ViewMyBidsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ViewMyBidsComponent]
    });
    fixture = TestBed.createComponent(ViewMyBidsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
