import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookACarComponent } from './book-a-car.component';

describe('BookACarComponent', () => {
  let component: BookACarComponent;
  let fixture: ComponentFixture<BookACarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BookACarComponent]
    });
    fixture = TestBed.createComponent(BookACarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
