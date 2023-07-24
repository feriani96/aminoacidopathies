import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICassuspecte } from '../cassuspecte.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../cassuspecte.test-samples';

import { CassuspecteService } from './cassuspecte.service';

const requireRestSample: ICassuspecte = {
  ...sampleWithRequiredData,
};

describe('Cassuspecte Service', () => {
  let service: CassuspecteService;
  let httpMock: HttpTestingController;
  let expectedResult: ICassuspecte | ICassuspecte[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CassuspecteService);
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

    it('should create a Cassuspecte', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const cassuspecte = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(cassuspecte).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Cassuspecte', () => {
      const cassuspecte = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(cassuspecte).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Cassuspecte', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Cassuspecte', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Cassuspecte', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCassuspecteToCollectionIfMissing', () => {
      it('should add a Cassuspecte to an empty array', () => {
        const cassuspecte: ICassuspecte = sampleWithRequiredData;
        expectedResult = service.addCassuspecteToCollectionIfMissing([], cassuspecte);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cassuspecte);
      });

      it('should not add a Cassuspecte to an array that contains it', () => {
        const cassuspecte: ICassuspecte = sampleWithRequiredData;
        const cassuspecteCollection: ICassuspecte[] = [
          {
            ...cassuspecte,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCassuspecteToCollectionIfMissing(cassuspecteCollection, cassuspecte);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Cassuspecte to an array that doesn't contain it", () => {
        const cassuspecte: ICassuspecte = sampleWithRequiredData;
        const cassuspecteCollection: ICassuspecte[] = [sampleWithPartialData];
        expectedResult = service.addCassuspecteToCollectionIfMissing(cassuspecteCollection, cassuspecte);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cassuspecte);
      });

      it('should add only unique Cassuspecte to an array', () => {
        const cassuspecteArray: ICassuspecte[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const cassuspecteCollection: ICassuspecte[] = [sampleWithRequiredData];
        expectedResult = service.addCassuspecteToCollectionIfMissing(cassuspecteCollection, ...cassuspecteArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const cassuspecte: ICassuspecte = sampleWithRequiredData;
        const cassuspecte2: ICassuspecte = sampleWithPartialData;
        expectedResult = service.addCassuspecteToCollectionIfMissing([], cassuspecte, cassuspecte2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(cassuspecte);
        expect(expectedResult).toContain(cassuspecte2);
      });

      it('should accept null and undefined values', () => {
        const cassuspecte: ICassuspecte = sampleWithRequiredData;
        expectedResult = service.addCassuspecteToCollectionIfMissing([], null, cassuspecte, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(cassuspecte);
      });

      it('should return initial array if no Cassuspecte is added', () => {
        const cassuspecteCollection: ICassuspecte[] = [sampleWithRequiredData];
        expectedResult = service.addCassuspecteToCollectionIfMissing(cassuspecteCollection, undefined, null);
        expect(expectedResult).toEqual(cassuspecteCollection);
      });
    });

    describe('compareCassuspecte', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCassuspecte(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCassuspecte(entity1, entity2);
        const compareResult2 = service.compareCassuspecte(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCassuspecte(entity1, entity2);
        const compareResult2 = service.compareCassuspecte(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCassuspecte(entity1, entity2);
        const compareResult2 = service.compareCassuspecte(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
