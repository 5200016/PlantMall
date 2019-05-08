/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SysCouponService } from 'app/entities/sys-coupon/sys-coupon.service';
import { ISysCoupon, SysCoupon } from 'app/shared/model/sys-coupon.model';

describe('Service Tests', () => {
    describe('SysCoupon Service', () => {
        let injector: TestBed;
        let service: SysCouponService;
        let httpMock: HttpTestingController;
        let elemDefault: ISysCoupon;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(SysCouponService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new SysCoupon(0, 0, 'AAAAAAA', 0, 0, 0, 0, currentDate, currentDate, 'AAAAAAA', 0, 0, currentDate, currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        startTime: currentDate.format(DATE_TIME_FORMAT),
                        endTime: currentDate.format(DATE_TIME_FORMAT),
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

            it('should create a SysCoupon', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        startTime: currentDate.format(DATE_TIME_FORMAT),
                        endTime: currentDate.format(DATE_TIME_FORMAT),
                        createTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        startTime: currentDate,
                        endTime: currentDate,
                        createTime: currentDate,
                        updateTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new SysCoupon(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a SysCoupon', async () => {
                const returnedFromService = Object.assign(
                    {
                        type: 1,
                        name: 'BBBBBB',
                        value: 1,
                        quantity: 1,
                        getNumber: 1,
                        limit: 1,
                        startTime: currentDate.format(DATE_TIME_FORMAT),
                        endTime: currentDate.format(DATE_TIME_FORMAT),
                        description: 'BBBBBB',
                        moneyOff: 1,
                        range: 1,
                        createTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        startTime: currentDate,
                        endTime: currentDate,
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

            it('should return a list of SysCoupon', async () => {
                const returnedFromService = Object.assign(
                    {
                        type: 1,
                        name: 'BBBBBB',
                        value: 1,
                        quantity: 1,
                        getNumber: 1,
                        limit: 1,
                        startTime: currentDate.format(DATE_TIME_FORMAT),
                        endTime: currentDate.format(DATE_TIME_FORMAT),
                        description: 'BBBBBB',
                        moneyOff: 1,
                        range: 1,
                        createTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        startTime: currentDate,
                        endTime: currentDate,
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

            it('should delete a SysCoupon', async () => {
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
