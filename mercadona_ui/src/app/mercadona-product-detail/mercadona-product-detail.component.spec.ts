import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MercadonaProductDetailComponent } from './mercadona-product-detail.component';

describe('MercadonaProductDetailComponent', () => {
  let component: MercadonaProductDetailComponent;
  let fixture: ComponentFixture<MercadonaProductDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MercadonaProductDetailComponent]
    });
    fixture = TestBed.createComponent(MercadonaProductDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
