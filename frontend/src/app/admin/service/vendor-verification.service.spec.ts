import { TestBed } from '@angular/core/testing';

import { VendorVerificationService } from './vendor-verification.service';

describe('VendorVerificationService', () => {
  let service: VendorVerificationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VendorVerificationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
