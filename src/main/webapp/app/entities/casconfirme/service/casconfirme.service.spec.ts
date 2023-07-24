import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICasconfirme } from '../casconfirme.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../casconfirme.test-samples';

import { CasconfirmeService } from './casconfirme.service';

const requireRestSample: ICasconfirme = {
  ...sampleWithRequiredData,
};

describe('Casconfirme Service', () => {
  let service: CasconfirmeService;
  let httpMock: HttpTestingController;
  let expectedResult: ICasconfirme | ICasconfirme[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CasconfirmeService);
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

    it('should create a Casconfirme', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const casconfirme = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(casconfirme).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Casconfirme', () => {
      const casconfirme = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(casconfirme).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Casconfirme', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Casconfirme', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Casconfirme', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCasconfirmeToCollectionIfMissing', () => {
      it('should add a Casconfirme to an empty array', () => {
        const casconfirme: ICasconfirme = sampleWithRequiredData;
        expectedResult = service.addCasconfirmeToCollectionIfMissing([], casconfirme);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(casconfirme);
      });

      it('should not add a Casconfirme to an array that contains it', () => {
        const casconfirme: ICasconfirme = sampleWithRequiredData;
        const casconfirmeCollection: ICasconfirme[] = [
          {
            ...casconfirme,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCasconfirmeToCollectionIfMissing(casconfirmeCollection, casconfirme);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Casconfirme to an array that doesn't contain it", () => {
        const casconfirme: ICasconfirme = sampleWithRequiredData;
        const casconfirmeCollection: ICasconfirme[] = [sampleWithPartialData];
        expectedResult = service.addCasconfirmeToCollectionIfMissing(casconfirmeCollection, casconfirme);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(casconfirme);
      });

      it('should add only unique Casconfirme to an array', () => {
        const casconfirmeArray: ICasconfirme[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const casconfirmeCollection: ICasconfirme[] = [sampleWithRequiredData];
        expectedResult = service.addCasconfirmeToCollectionIfMissing(casconfirmeCollection, ...casconfirmeArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const casconfirme: ICasconfirme = sampleWithRequiredData;
        const casconfirme2: ICasconfirme = sampleWithPartialData;
        expectedResult = service.addCasconfirmeToCollectionIfMissing([], casconfirme, casconfirme2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(casconfirme);
        expect(expectedResult).toContain(casconfirme2);
      });

      it('should accept null and undefined values', () => {
        const casconfirme: ICasconfirme = sampleWithRequiredData;
        expectedResult = service.addCasconfirmeToCollectionIfMissing([], null, casconfirme, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(casconfirme);
      });

      it('should return initial array if no Casconfirme is added', () => {
        const casconfirmeCollection: ICasconfirme[] = [sampleWithRequiredData];
        expectedResult = service.addCasconfirmeToCollectionIfMissing(casconfirmeCollection, undefined, null);
        expect(expectedResult).toEqual(casconfirmeCollection);
      });
    });

    describe('compareCasconfirme', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCasconfirme(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCasconfirme(entity1, entity2);
        const compareResult2 = service.compareCasconfirme(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCasconfirme(entity1, entity2);
        const compareResult2 = service.compareCasconfirme(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCasconfirme(entity1, entity2);
        const compareResult2 = service.compareCasconfirme(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
