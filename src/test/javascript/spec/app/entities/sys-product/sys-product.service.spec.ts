/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SysProductService } from 'app/entities/sys-product/sys-product.service';
import { ISysProduct, SysProduct } from 'app/shared/model/sys-product.model';

describe('Service Tests', () => {
    describe('SysProduct Service', () => {
        let injector: TestBed;
        let service: SysProductService;
        let httpMock: HttpTestingController;
        let elemDefault: ISysProduct;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(SysProductService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new SysProduct(0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', currentDate, currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        createTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a SysProduct', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        createTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        createTime: currentDate,
                        updateTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new SysProduct(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a SysProduct', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        price: 1,
                        image: 'BBBBBB',
                        specification: 'BBBBBB',
                        inventory: 1,
                        sale: 1,
                        description: 'BBBBBB',
                        createTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        createTime: currentDate,
                        updateTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of SysProduct', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        price: 1,
                        image: 'BBBBBB',
                        specification: 'BBBBBB',
                        inventory: 1,
                        sale: 1,
                        description: 'BBBBBB',
                        createTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        createTime: currentDate,
                        updateTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a SysProduct', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});