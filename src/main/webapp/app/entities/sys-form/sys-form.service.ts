import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISysForm } from 'app/shared/model/sys-form.model';

type EntityResponseType = HttpResponse<ISysForm>;
type EntityArrayResponseType = HttpResponse<ISysForm[]>;

@Injectable({ providedIn: 'root' })
export class SysFormService {
    public resourceUrl = SERVER_API_URL + 'api/sys-forms';

    constructor(protected http: HttpClient) {}

    create(sysForm: ISysForm): Observable<EntityResponseType> {
        return this.http.post<ISysForm>(this.resourceUrl, sysForm, { observe: 'response' });
    }

    update(sysForm: ISysForm): Observable<EntityResponseType> {
        return this.http.put<ISysForm>(this.resourceUrl, sysForm, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISysForm>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISysForm[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
