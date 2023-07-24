import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CasconfirmeDetailComponent } from './casconfirme-detail.component';

describe('Casconfirme Management Detail Component', () => {
  let comp: CasconfirmeDetailComponent;
  let fixture: ComponentFixture<CasconfirmeDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CasconfirmeDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ casconfirme: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(CasconfirmeDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CasconfirmeDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load casconfirme on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.casconfirme).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
