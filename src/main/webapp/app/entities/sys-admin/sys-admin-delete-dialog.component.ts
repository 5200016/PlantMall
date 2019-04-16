import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysAdmin } from 'app/shared/model/sys-admin.model';
import { SysAdminService } from './sys-admin.service';

@Component({
    selector: 'jhi-sys-admin-delete-dialog',
    templateUrl: './sys-admin-delete-dialog.component.html'
})
export class SysAdminDeleteDialogComponent {
    sysAdmin: ISysAdmin;

    constructor(protected sysAdminService: SysAdminService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysAdminService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysAdminListModification',
                content: 'Deleted an sysAdmin'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-admin-delete-popup',
    template: ''
})
export class SysAdminDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysAdmin }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysAdminDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.sysAdmin = sysAdmin;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
