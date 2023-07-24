import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { StructuresanteService } from '../service/structuresante.service';

import { StructuresanteComponent } from './structuresante.component';

describe('Structuresante Management Component', () => {
  let comp: StructuresanteComponent;
  let fixture: ComponentFixture<StructuresanteComponent>;
  let service: StructuresanteService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'structuresante', component: StructuresanteComponent }]), HttpClientTestingModule],
      declarations: [StructuresanteComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(StructuresanteComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(StructuresanteComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(StructuresanteService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.structuresantes?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });

  describe('trackId', () => {
    it('Should forward to structuresanteService', () => {
      const entity = { id: 123 };
      jest.spyOn(service, 'getStructuresanteIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getStructuresanteIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
