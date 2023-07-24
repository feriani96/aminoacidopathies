import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IStructurefiche } from '../structurefiche.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../structurefiche.test-samples';

import { StructureficheService } from './structurefiche.service';

const requireRestSample: IStructurefiche = {
  ...sampleWithRequiredData,
};

describe('Structurefiche Service', () => {
  let service: StructureficheService;
  let httpMock: HttpTestingController;
  let expectedResult: IStructurefiche | IStructurefiche[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(StructureficheService);
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

    it('should create a Structurefiche', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const structurefiche = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(structurefiche).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Structurefiche', () => {
      const structurefiche = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(structurefiche).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Structurefiche', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Structurefiche', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Structurefiche', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addStructureficheToCollectionIfMissing', () => {
      it('should add a Structurefiche to an empty array', () => {
        const structurefiche: IStructurefiche = sampleWithRequiredData;
        expectedResult = service.addStructureficheToCollectionIfMissing([], structurefiche);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(structurefiche);
      });

      it('should not add a Structurefiche to an array that contains it', () => {
        const structurefiche: IStructurefiche = sampleWithRequiredData;
        const structureficheCollection: IStructurefiche[] = [
          {
            ...structurefiche,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addStructureficheToCollectionIfMissing(structureficheCollection, structurefiche);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Structurefiche to an array that doesn't contain it", () => {
        const structurefiche: IStructurefiche = sampleWithRequiredData;
        const structureficheCollection: IStructurefiche[] = [sampleWithPartialData];
        expectedResult = service.addStructureficheToCollectionIfMissing(structureficheCollection, structurefiche);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(structurefiche);
      });

      it('should add only unique Structurefiche to an array', () => {
        const structureficheArray: IStructurefiche[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const structureficheCollection: IStructurefiche[] = [sampleWithRequiredData];
        expectedResult = service.addStructureficheToCollectionIfMissing(structureficheCollection, ...structureficheArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const structurefiche: IStructurefiche = sampleWithRequiredData;
        const structurefiche2: IStructurefiche = sampleWithPartialData;
        expectedResult = service.addStructureficheToCollectionIfMissing([], structurefiche, structurefiche2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(structurefiche);
        expect(expectedResult).toContain(structurefiche2);
      });

      it('should accept null and undefined values', () => {
        const structurefiche: IStructurefiche = sampleWithRequiredData;
        expectedResult = service.addStructureficheToCollectionIfMissing([], null, structurefiche, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(structurefiche);
      });

      it('should return initial array if no Structurefiche is added', () => {
        const structureficheCollection: IStructurefiche[] = [sampleWithRequiredData];
        expectedResult = service.addStructureficheToCollectionIfMissing(structureficheCollection, undefined, null);
        expect(expectedResult).toEqual(structureficheCollection);
      });
    });

    describe('compareStructurefiche', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareStructurefiche(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareStructurefiche(entity1, entity2);
        const compareResult2 = service.compareStructurefiche(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareStructurefiche(entity1, entity2);
        const compareResult2 = service.compareStructurefiche(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareStructurefiche(entity1, entity2);
        const compareResult2 = service.compareStructurefiche(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
