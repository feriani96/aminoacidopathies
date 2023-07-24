import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CassuspecteDetailComponent } from './cassuspecte-detail.component';

describe('Cassuspecte Management Detail Component', () => {
  let comp: CassuspecteDetailComponent;
  let fixture: ComponentFixture<CassuspecteDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CassuspecteDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ cassuspecte: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CassuspecteDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CassuspecteDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load cassuspecte on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.cassuspecte).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
