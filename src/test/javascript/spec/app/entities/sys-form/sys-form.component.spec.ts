/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysFormComponent } from 'app/entities/sys-form/sys-form.component';
import { SysFormService } from 'app/entities/sys-form/sys-form.service';
import { SysForm } from 'app/shared/model/sys-form.model';

describe('Component Tests', () => {
    describe('SysForm Management Component', () => {
        let comp: SysFormComponent;
        let fixture: ComponentFixture<SysFormComponent>;
        let service: SysFormService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysFormComponent],
                providers: []
            })
                .overrideTemplate(SysFormComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysFormComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysFormService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysForm(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysForms[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
