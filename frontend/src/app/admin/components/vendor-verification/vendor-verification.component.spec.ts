import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendorVerificationComponent } from './vendor-verification.component';

describe('VendorVerificationComponent', () => {
  let component: VendorVerificationComponent;
  let fixture: ComponentFixture<VendorVerificationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VendorVerificationComponent]
    });
    fixture = TestBed.createComponent(VendorVerificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
