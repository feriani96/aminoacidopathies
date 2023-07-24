import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StructuresanteDetailComponent } from './structuresante-detail.component';

describe('Structuresante Management Detail Component', () => {
  let comp: StructuresanteDetailComponent;
  let fixture: ComponentFixture<StructuresanteDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StructuresanteDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ structuresante: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(StructuresanteDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(StructuresanteDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load structuresante on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.structuresante).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
