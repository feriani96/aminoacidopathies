import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IMetabolique } from '../metabolique.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../metabolique.test-samples';

import { MetaboliqueService } from './metabolique.service';

const requireRestSample: IMetabolique = {
  ...sampleWithRequiredData,
};

describe('Metabolique Service', () => {
  let service: MetaboliqueService;
  let httpMock: HttpTestingController;
  let expectedResult: IMetabolique | IMetabolique[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(MetaboliqueService);
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

    it('should create a Metabolique', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const metabolique = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(metabolique).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Metabolique', () => {
      const metabolique = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(metabolique).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Metabolique', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Metabolique', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Metabolique', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addMetaboliqueToCollectionIfMissing', () => {
      it('should add a Metabolique to an empty array', () => {
        const metabolique: IMetabolique = sampleWithRequiredData;
        expectedResult = service.addMetaboliqueToCollectionIfMissing([], metabolique);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(metabolique);
      });

      it('should not add a Metabolique to an array that contains it', () => {
        const metabolique: IMetabolique = sampleWithRequiredData;
        const metaboliqueCollection: IMetabolique[] = [
          {
            ...metabolique,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addMetaboliqueToCollectionIfMissing(metaboliqueCollection, metabolique);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Metabolique to an array that doesn't contain it", () => {
        const metabolique: IMetabolique = sampleWithRequiredData;
        const metaboliqueCollection: IMetabolique[] = [sampleWithPartialData];
        expectedResult = service.addMetaboliqueToCollectionIfMissing(metaboliqueCollection, metabolique);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(metabolique);
      });

      it('should add only unique Metabolique to an array', () => {
        const metaboliqueArray: IMetabolique[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const metaboliqueCollection: IMetabolique[] = [sampleWithRequiredData];
        expectedResult = service.addMetaboliqueToCollectionIfMissing(metaboliqueCollection, ...metaboliqueArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const metabolique: IMetabolique = sampleWithRequiredData;
        const metabolique2: IMetabolique = sampleWithPartialData;
        expectedResult = service.addMetaboliqueToCollectionIfMissing([], metabolique, metabolique2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(metabolique);
        expect(expectedResult).toContain(metabolique2);
      });

      it('should accept null and undefined values', () => {
        const metabolique: IMetabolique = sampleWithRequiredData;
        expectedResult = service.addMetaboliqueToCollectionIfMissing([], null, metabolique, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(metabolique);
      });

      it('should return initial array if no Metabolique is added', () => {
        const metaboliqueCollection: IMetabolique[] = [sampleWithRequiredData];
        expectedResult = service.addMetaboliqueToCollectionIfMissing(metaboliqueCollection, undefined, null);
        expect(expectedResult).toEqual(metaboliqueCollection);
      });
    });

    describe('compareMetabolique', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareMetabolique(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareMetabolique(entity1, entity2);
        const compareResult2 = service.compareMetabolique(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareMetabolique(entity1, entity2);
        const compareResult2 = service.compareMetabolique(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareMetabolique(entity1, entity2);
        const compareResult2 = service.compareMetabolique(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
