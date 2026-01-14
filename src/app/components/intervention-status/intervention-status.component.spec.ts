import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterventionStatusComponent } from './intervention-status.component';

describe('InterventionStatusComponent', () => {
  let component: InterventionStatusComponent;
  let fixture: ComponentFixture<InterventionStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InterventionStatusComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InterventionStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
