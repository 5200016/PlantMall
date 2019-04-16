import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISysMemberLevel } from 'app/shared/model/sys-member-level.model';
import { SysMemberLevelService } from './sys-member-level.service';

@Component({
    selector: 'jhi-sys-member-level-delete-dialog',
    templateUrl: './sys-member-level-delete-dialog.component.html'
})
export class SysMemberLevelDeleteDialogComponent {
    sysMemberLevel: ISysMemberLevel;

    constructor(
        protected sysMemberLevelService: SysMemberLevelService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.sysMemberLevelService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'sysMemberLevelListModification',
                content: 'Deleted an sysMemberLevel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-sys-member-level-delete-popup',
    template: ''
})
export class SysMemberLevelDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sysMemberLevel }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SysMemberLevelDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.sysMemberLevel = sysMemberLevel;
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
