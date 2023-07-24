import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CasdecesbasageDetailComponent } from './casdecesbasage-detail.component';

describe('Casdecesbasage Management Detail Component', () => {
  let comp: CasdecesbasageDetailComponent;
  let fixture: ComponentFixture<CasdecesbasageDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CasdecesbasageDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ casdecesbasage: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CasdecesbasageDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CasdecesbasageDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load casdecesbasage on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.casdecesbasage).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
