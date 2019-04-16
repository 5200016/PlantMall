import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysCustomerService } from 'app/shared/model/sys-customer-service.model';

type EntityResponseType = HttpResponse<ISysCustomerService>;
type EntityArrayResponseType = HttpResponse<ISysCustomerService[]>;

@Injectable({ providedIn: 'root' })
export class SysCustomerServiceService {
    public resourceUrl = SERVER_API_URL + 'api/sys-customer-services';

    constructor(protected http: HttpClient) {}

    create(sysCustomerService: ISysCustomerService): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysCustomerService);
        return this.http
            .post<ISysCustomerService>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sysCustomerService: ISysCustomerService): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sysCustomerService);
        return this.http
            .put<ISysCustomerService>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISysCustomerService>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISysCustomerService[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(sysCustomerService: ISysCustomerService): ISysCustomerService {
        const copy: ISysCustomerService = Object.assign({}, sysCustomerService, {
            createTime:
                sysCustomerService.createTime != null && sysCustomerService.createTime.isValid()
                    ? sysCustomerService.createTime.toJSON()
                    : null,
            updateTime:
                sysCustomerService.updateTime != null && sysCustomerService.updateTime.isValid()
                    ? sysCustomerService.updateTime.toJSON()
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
            res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((sysCustomerService: ISysCustomerService) => {
                sysCustomerService.createTime = sysCustomerService.createTime != null ? moment(sysCustomerService.createTime) : null;
                sysCustomerService.updateTime = sysCustomerService.updateTime != null ? moment(sysCustomerService.updateTime) : null;
            });
        }
        return res;
    }
}
