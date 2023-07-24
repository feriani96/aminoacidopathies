import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ServicesanteDetailComponent } from './servicesante-detail.component';

describe('Servicesante Management Detail Component', () => {
  let comp: ServicesanteDetailComponent;
  let fixture: ComponentFixture<ServicesanteDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ServicesanteDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ servicesante: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(ServicesanteDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ServicesanteDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load servicesante on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.servicesante).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
