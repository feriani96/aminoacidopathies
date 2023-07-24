import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ICasdecesbasage } from '../casdecesbasage.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../casdecesbasage.test-samples';

import { CasdecesbasageService } from './casdecesbasage.service';

const requireRestSample: ICasdecesbasage = {
  ...sampleWithRequiredData,
};

describe('Casdecesbasage Service', () => {
  let service: CasdecesbasageService;
  let httpMock: HttpTestingController;
  let expectedResult: ICasdecesbasage | ICasdecesbasage[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CasdecesbasageService);
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

    it('should create a Casdecesbasage', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const casdecesbasage = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(casdecesbasage).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Casdecesbasage', () => {
      const casdecesbasage = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(casdecesbasage).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Casdecesbasage', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Casdecesbasage', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Casdecesbasage', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCasdecesbasageToCollectionIfMissing', () => {
      it('should add a Casdecesbasage to an empty array', () => {
        const casdecesbasage: ICasdecesbasage = sampleWithRequiredData;
        expectedResult = service.addCasdecesbasageToCollectionIfMissing([], casdecesbasage);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(casdecesbasage);
      });

      it('should not add a Casdecesbasage to an array that contains it', () => {
        const casdecesbasage: ICasdecesbasage = sampleWithRequiredData;
        const casdecesbasageCollection: ICasdecesbasage[] = [
          {
            ...casdecesbasage,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCasdecesbasageToCollectionIfMissing(casdecesbasageCollection, casdecesbasage);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Casdecesbasage to an array that doesn't contain it", () => {
        const casdecesbasage: ICasdecesbasage = sampleWithRequiredData;
        const casdecesbasageCollection: ICasdecesbasage[] = [sampleWithPartialData];
        expectedResult = service.addCasdecesbasageToCollectionIfMissing(casdecesbasageCollection, casdecesbasage);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(casdecesbasage);
      });

      it('should add only unique Casdecesbasage to an array', () => {
        const casdecesbasageArray: ICasdecesbasage[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const casdecesbasageCollection: ICasdecesbasage[] = [sampleWithRequiredData];
        expectedResult = service.addCasdecesbasageToCollectionIfMissing(casdecesbasageCollection, ...casdecesbasageArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const casdecesbasage: ICasdecesbasage = sampleWithRequiredData;
        const casdecesbasage2: ICasdecesbasage = sampleWithPartialData;
        expectedResult = service.addCasdecesbasageToCollectionIfMissing([], casdecesbasage, casdecesbasage2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(casdecesbasage);
        expect(expectedResult).toContain(casdecesbasage2);
      });

      it('should accept null and undefined values', () => {
        const casdecesbasage: ICasdecesbasage = sampleWithRequiredData;
        expectedResult = service.addCasdecesbasageToCollectionIfMissing([], null, casdecesbasage, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(casdecesbasage);
      });

      it('should return initial array if no Casdecesbasage is added', () => {
        const casdecesbasageCollection: ICasdecesbasage[] = [sampleWithRequiredData];
        expectedResult = service.addCasdecesbasageToCollectionIfMissing(casdecesbasageCollection, undefined, null);
        expect(expectedResult).toEqual(casdecesbasageCollection);
      });
    });

    describe('compareCasdecesbasage', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCasdecesbasage(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareCasdecesbasage(entity1, entity2);
        const compareResult2 = service.compareCasdecesbasage(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareCasdecesbasage(entity1, entity2);
        const compareResult2 = service.compareCasdecesbasage(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareCasdecesbasage(entity1, entity2);
        const compareResult2 = service.compareCasdecesbasage(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
