import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DragonComponent } from './dragon.component';

describe('DragonComponent', () => {
  let component: DragonComponent;
  let fixture: ComponentFixture<DragonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DragonComponent]
    });
    fixture = TestBed.createComponent(DragonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
