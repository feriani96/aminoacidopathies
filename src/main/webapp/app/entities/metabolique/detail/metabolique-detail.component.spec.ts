import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MetaboliqueDetailComponent } from './metabolique-detail.component';

describe('Metabolique Management Detail Component', () => {
  let comp: MetaboliqueDetailComponent;
  let fixture: ComponentFixture<MetaboliqueDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MetaboliqueDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ metabolique: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(MetaboliqueDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(MetaboliqueDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load metabolique on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.metabolique).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
