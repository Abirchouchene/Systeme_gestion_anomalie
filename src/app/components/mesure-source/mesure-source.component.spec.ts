import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesureSourceComponent } from './mesure-source.component';

describe('MesureSourceComponent', () => {
  let component: MesureSourceComponent;
  let fixture: ComponentFixture<MesureSourceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MesureSourceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MesureSourceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
