import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { StructureficheDetailComponent } from './structurefiche-detail.component';

describe('Structurefiche Management Detail Component', () => {
  let comp: StructureficheDetailComponent;
  let fixture: ComponentFixture<StructureficheDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StructureficheDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ structurefiche: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(StructureficheDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(StructureficheDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load structurefiche on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.structurefiche).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
