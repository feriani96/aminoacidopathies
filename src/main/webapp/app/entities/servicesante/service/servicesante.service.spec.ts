import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IServicesante } from '../servicesante.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../servicesante.test-samples';

import { ServicesanteService } from './servicesante.service';

const requireRestSample: IServicesante = {
  ...sampleWithRequiredData,
};

describe('Servicesante Service', () => {
  let service: ServicesanteService;
  let httpMock: HttpTestingController;
  let expectedResult: IServicesante | IServicesante[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ServicesanteService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Servicesante', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const servicesante = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(servicesante).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Servicesante', () => {
      const servicesante = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(servicesante).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Servicesante', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Servicesante', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Servicesante', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addServicesanteToCollectionIfMissing', () => {
      it('should add a Servicesante to an empty array', () => {
        const servicesante: IServicesante = sampleWithRequiredData;
        expectedResult = service.addServicesanteToCollectionIfMissing([], servicesante);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(servicesante);
      });

      it('should not add a Servicesante to an array that contains it', () => {
        const servicesante: IServicesante = sampleWithRequiredData;
        const servicesanteCollection: IServicesante[] = [
          {
            ...servicesante,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addServicesanteToCollectionIfMissing(servicesanteCollection, servicesante);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Servicesante to an array that doesn't contain it", () => {
        const servicesante: IServicesante = sampleWithRequiredData;
        const servicesanteCollection: IServicesante[] = [sampleWithPartialData];
        expectedResult = service.addServicesanteToCollectionIfMissing(servicesanteCollection, servicesante);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(servicesante);
      });

      it('should add only unique Servicesante to an array', () => {
        const servicesanteArray: IServicesante[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const servicesanteCollection: IServicesante[] = [sampleWithRequiredData];
        expectedResult = service.addServicesanteToCollectionIfMissing(servicesanteCollection, ...servicesanteArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const servicesante: IServicesante = sampleWithRequiredData;
        const servicesante2: IServicesante = sampleWithPartialData;
        expectedResult = service.addServicesanteToCollectionIfMissing([], servicesante, servicesante2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(servicesante);
        expect(expectedResult).toContain(servicesante2);
      });

      it('should accept null and undefined values', () => {
        const servicesante: IServicesante = sampleWithRequiredData;
        expectedResult = service.addServicesanteToCollectionIfMissing([], null, servicesante, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(servicesante);
      });

      it('should return initial array if no Servicesante is added', () => {
        const servicesanteCollection: IServicesante[] = [sampleWithRequiredData];
        expectedResult = service.addServicesanteToCollectionIfMissing(servicesanteCollection, undefined, null);
        expect(expectedResult).toEqual(servicesanteCollection);
      });
    });

    describe('compareServicesante', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareServicesante(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareServicesante(entity1, entity2);
        const compareResult2 = service.compareServicesante(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareServicesante(entity1, entity2);
        const compareResult2 = service.compareServicesante(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareServicesante(entity1, entity2);
        const compareResult2 = service.compareServicesante(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
