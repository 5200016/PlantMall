import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SysForm } from 'app/shared/model/sys-form.model';
import { SysFormService } from './sys-form.service';
import { SysFormComponent } from './sys-form.component';
import { SysFormDetailComponent } from './sys-form-detail.component';
import { SysFormUpdateComponent } from './sys-form-update.component';
import { SysFormDeletePopupComponent } from './sys-form-delete-dialog.component';
import { ISysForm } from 'app/shared/model/sys-form.model';

@Injectable({ providedIn: 'root' })
export class SysFormResolve implements Resolve<ISysForm> {
    constructor(private service: SysFormService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<SysForm> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SysForm>) => response.ok),
                map((sysForm: HttpResponse<SysForm>) => sysForm.body)
            );
        }
        return of(new SysForm());
    }
}

export const sysFormRoute: Routes = [
    {
        path: 'sys-form',
        component: SysFormComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysForm.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-form/:id/view',
        component: SysFormDetailComponent,
        resolve: {
            sysForm: SysFormResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysForm.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-form/new',
        component: SysFormUpdateComponent,
        resolve: {
            sysForm: SysFormResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysForm.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sys-form/:id/edit',
        component: SysFormUpdateComponent,
        resolve: {
            sysForm: SysFormResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysForm.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sysFormPopupRoute: Routes = [
    {
        path: 'sys-form/:id/delete',
        component: SysFormDeletePopupComponent,
        resolve: {
            sysForm: SysFormResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plantMallApp.sysForm.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
