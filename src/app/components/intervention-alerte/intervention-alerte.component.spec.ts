import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InterventionAlerteComponent } from './intervention-alerte.component';

describe('InterventionAlerteComponent', () => {
  let component: InterventionAlerteComponent;
  let fixture: ComponentFixture<InterventionAlerteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InterventionAlerteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InterventionAlerteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
